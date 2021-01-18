import React, { useState, useEffect } from 'react'
import namedata from './services/names.js'
import Header from './components/Header'
import Names from './components/Names'
import {sortModes, SortButton} from './components/SortButton'

const App = () => {
  const [ names, setNames ] = useState([])
  const [ filteredNames, setFilteredNames ] = useState([])
  const [ inputValue, setInputValue ] = useState('')
  const [ selectedMode, setSelectedMode ] = useState(sortModes.BY_AMOUNT)

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

  const handleSortAlphabetically = () => {
    setSelectedMode(sortModes.ALPHABETICALLY)
    setFilteredNames(Array.from(filteredNames).sort((a, b) => {
      if (a.name < b.name) { return -1; }
      if (a.name > b.name) { return 1; }
      return 0;
    }))
  }

  const handleSortByAmount = () => {
    setSelectedMode(sortModes.BY_AMOUNT)
    setFilteredNames(Array.from(filteredNames).sort((a, b) => b.amount - a.amount))
  }

  const handleInputChange = (event) => {
    setInputValue(event.target.value)
  }

  return (
    <>
      <Header title={'Name App'} />
      <Names names={filteredNames} inputValue={inputValue} handleInputChange={handleInputChange} />
      
      <SortButton 
        text="Sort by amount"
        sortMode={sortModes.BY_AMOUNT}
        selectedMode={selectedMode}
        handleClick={handleSortByAmount}
      />
      <SortButton
        text="Sort alphabetically"
        sortMode={sortModes.ALPHABETICALLY}
        selectedMode={selectedMode}
        handleClick={handleSortAlphabetically}
      />
    </>
  )
}

export default App