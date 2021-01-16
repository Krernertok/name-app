import React, {useState, useEffect} from 'react'
import namedata from './services/names.js'
import Header from './components/Header'
import Names from './components/Names'

const App = () => {
  const [ names, setNames ] = useState([])

  useEffect(() => {
    console.log
    namedata.getNames().then((data => {
      setNames(data.names)
    }))
  })

  return (
    <>
      <Header title={'Name App'} />
      <Names names={names} />
    </>
  )
}

export default App