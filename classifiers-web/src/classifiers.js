import React from 'react'
import PropTypes from 'prop-types'
import './classifiers.css'

const Classifier = ({classifier}) =>{
  console.log(classifier)
  const list = classifier.genes.map( gene => <tr key={gene}><td>{gene}</td></tr>)
  return <span className="classifier">
      {classifier.name}: {list.length} genes
    </span>
}


const Clasifiers = (props) => {
  return <div>{
        props.classifiers.map( (c, i) =>
          <Classifier key={i} classifier={c} />
        )
    }</div>
}

Clasifiers.propTypes = {
  classifiers : PropTypes.array
}

export default Clasifiers
