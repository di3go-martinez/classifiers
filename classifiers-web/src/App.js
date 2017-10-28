import React, { Component } from 'react';
import './App.css';
import Classifier from './classifiers.js'

class App extends Component {

  constructor(props){
    super(props);
    this.state = {classifiers: []};
  }

  componentWillMount(){
    fetch("http://localhost:8080/functions?author=di3go")
      .then((response) => {
        if (!response.ok) {
          console.log(response);
          return [];
        }
        return response.json();
      })
      .then((recurso) => {
        this.setState({classifiers:recurso});
      });

  }
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Bioplat Classifiers</h1>
        </header>
        <div className="App-intro">
         <Classifier classifiers={this.state.classifiers}/>
        </div>
      </div>
    );
  }
}

export default App;
