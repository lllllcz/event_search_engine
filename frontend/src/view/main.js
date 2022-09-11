import React from "react";
import SearchBar from "../component/search_bar";
import SearchResults from "../component/results";
import MyHeader from "../component/web_top";

let postRequest = (url, json, callback) => {

  let opts = {
    method: "POST",
    body: JSON.stringify(json),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: "include"
  };

  fetch(url,opts)
    .then((response) => {
      return response.json()
    })
    .then((data) => {
      callback(data);
    })
    .catch((error) => {
      console.log(error);
    });
};

class MainPage extends React.Component {

  constructor() {
    super();
    this.state = {
      auth: false,
    };
  }

  componentDidMount() {
    const url = `http://124.70.59.249:8080/checkSession`;
    const callback = (data) => {
      console.log(data)
      if (data.status >= 0) {
        this.setState({auth: true});
      }
    }
    postRequest(url, {}, callback);
  }

  render() {
    return (
      (this.state.auth) ? (
        <div>
        <MyHeader />
        <div className="container">
          <SearchResults />
        </div>
      </div>) : (<div />)
    );
  }


}

export default MainPage;