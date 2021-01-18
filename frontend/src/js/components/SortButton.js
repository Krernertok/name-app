import React from 'react'

export const sortModes = Object.freeze({
  BY_AMOUNT: 0,
  ALPHABETICALLY: 1
})

export const SortButton = ({text, handleClick, sortMode, selectedMode}) => {
  const activeStyles = "bg-orange-500 text-white px-2 py-1 hover:bg-orange-600 my-2 focus:outline-none focus:outline-orange"
  const inactiveStyles = "bg-orange-300 text-white px-2 py-1 hover:bg-orange-600 my-2 focus:outline-none focus:outline-orange"

  return <button type="button" 
          className={(selectedMode === sortMode) ? activeStyles : inactiveStyles}
          onClick={handleClick}>{text}</button>
}

