var UserItem = React.createClass({
  render: function () {
    return (
      <tr>
        <td>{this.props.email}</td>
      </tr>
    );
  }
});

var jsonData;

var UserList = React.createClass({
  render: function () {
    var testVar = this.props.users;
    console.log("testVar");
    console.log(testVar);
    var users = this.props.users.map(function (user, index) {
        console.log({testFetch});
        console.log({users});
        console.log({products});
        console.log({productsTest});

        console.log("test user");
        console.log(user);
        console.log("jsonData");
        console.log(jsonData);


      return (
        <UserItem
          key={index}
          email={user.email}
        />
      );
    });

    return (
      <table>
        {users}
      </table>
    );
  }
});


var fetchData = function() {
    return fetch('http://localhost:8080/user/all')
             .then(
               function(response) {
                 if (response.status !== 200) {
                   console.log('Looks like there was a problem. Status Code: ' +
                     response.status);
                   return;
                 }

                 // Examine the text in the response
                 return response.json().then(function(data) {
                   console.log("da data")
                   console.log(data);
                   jsonData = data;
                   return data;
                 });
               }
             )
             .catch(function(err) {
               console.log('Fetch Error :-S', err);
             });

}


var testFetch = fetch('http://localhost:8080/user/all')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      return response.json();
//      then(function(data) {
//        console.log("da data")
//        console.log(data);
//        jsonData = data;
//        return data;
//      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });




var users = fetch('http://localhost:8080/user/all')
                .then((response) => response.json());

// Could come from an API, LocalStorage, another component, etc...
var products = [fetchData];
var productsTest = {testFetch};

//fetchData().then(function(data) {console.log("Json result is " + data)})

//ReactDOM.render(<UserList users={products} />, document.getElementById('root'));
ReactDOM.render(<UserList users={products} />, document.getElementById('root'));