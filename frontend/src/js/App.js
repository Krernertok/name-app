import React, {useState, useEffect} from 'react'
import namedata from './services/names.js'
import Header from './components/Header'
import Names from './components/Names'
import Button from './components/Button'

const sortByAmountText = 'Sort by amount'
const sortAlphabeticallyText = 'Sort alphabetically'

const App = () => {
  const [ names, setNames ] = useState([])
  const [ sortText, setSortText ] = useState(sortByAmountText)

  useEffect(() => {
    namedata.getNames().then((data => {
      setNames(data.names)
    }))
  }, [])

  const toggleSort = (event) => {
    if (sortText === sortAlphabeticallyText) {
      
      setSortText(sortByAmountText)
      setNames(Array.from(names).sort((a, b) => {
        if (a.name < b.name) { return -1; }
        if (a.name > b.name) { return 1; }
        return 0;
      }))
      
    } else {
      
      setNames(Array.from(names).sort((a, b) => b.amount - a.amount))
      setSortText(sortAlphabeticallyText)

    }
  }

  return (
    <>
      <Header title={'Name App'} />
      <Names names={names} />
      <Button text={sortText} handleClick={toggleSort} />
    </>
  )
}

export default App