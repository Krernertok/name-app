import React, {useState, useEffect} from 'react'
import namedata from './services/names.js'
import Header from './components/Header'
import Names from './components/Names'
import Button from './components/Button'

const sortByAmountText = 'Sort by amount'
const sortAlphabeticallyText = 'Sort alphabetically'

const App = () => {
  const [ names, setNames ] = useState([])
  const [ filteredNames, setFilteredNames ] = useState([])
  const [ sortText, setSortText ] = useState(sortAlphabeticallyText)
  const [ inputValue, setInputValue ] = useState('')

  useEffect(() => {
    namedata.getNames().then((data => {
      setNames(data.names)
      setFilteredNames(data.names)
    }))
  }, [])

  useEffect(() => {
    if (inputValue === '') {
      setFilteredNames(names)
    } else {
      setFilteredNames(names.filter(name => {
        return name.name.toLowerCase().includes(inputValue.toLowerCase())
      }))
    }
  }, [inputValue])

  const toggleSort = (event) => {
    if (sortText === sortAlphabeticallyText) {
      
      setSortText(sortByAmountText)
      setFilteredNames(Array.from(filteredNames).sort((a, b) => {
        if (a.name < b.name) { return -1; }
        if (a.name > b.name) { return 1; }
        return 0;
      }))
      
    } else {
      
      setFilteredNames(Array.from(filteredNames).sort((a, b) => b.amount - a.amount))
      setSortText(sortAlphabeticallyText)

    }
  }

  const handleInputChange = (event) => {
    setInputValue(event.target.value)
  }

  return (
    <>
      <Header title={'Name App'} />
      <Names names={filteredNames} inputValue={inputValue} handleInputChange={handleInputChange} />
      <Button text={sortText} handleClick={toggleSort} />
    </>
  )
}

export default App