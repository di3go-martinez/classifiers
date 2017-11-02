import React, { Component } from 'react';
import './App.css';
import Classifier from './classifiers.js'

class App extends Component {

  constructor(props){
    super(props);
    this.state = {classifiers: []};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentWillMount(){
    this.search("bioplat")
  }

  search(authorName){
    fetch("http://localhost:8080/functions?author="+authorName)
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

  handleSubmit(event){
    event.preventDefault();
    this.search (this.authorInput.value);
  }
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Bioplat Classifiers</h1>
        </header>
        <div className="search">
        <form onSubmit={this.handleSubmit}>
          <input type="text" ref={author => this.authorInput = author} placeholder="Author Name"/>
          <input className="button" type="submit" value="search" />
        </form>
        </div>
        <div className="App-intro">
         <Classifier classifiers={this.state.classifiers}/>
        </div>
      </div>
    );
  }
}

export default App;
