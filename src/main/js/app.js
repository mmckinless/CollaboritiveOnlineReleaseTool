const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {users: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/user/all'}).done(response => {
			this.setState({user: response.entity.users});
		});
	}

	render() {
		return (
			<UserList users={this.state.users}/>
		)
	}
}

class UserList extends React.Component{
	render() {
		var users = this.props.users.map(user =>
			<User user={user}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Email</th>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
					{users}
				</tbody>
			</table>
		)
	}
}

class User extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.user.email}</td>
				<td>{this.props.user.firstName}</td>
				<td>{this.props.user.lastName}</td>
			</tr>
		)
	}
}


ReactDOM.render(
	<h1>Test</h1>,
	document.getElementById('react')
)