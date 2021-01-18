import React from 'react'

export const sortModes = Object.freeze({
  BY_AMOUNT: 0,
  ALPHABETICALLY: 1
})

export const SortButton = ({text, handleClick, sortMode, selectedMode}) => {
  const activeStyles = "bg-orange-500 text-white px-2 py-1 hover:bg-orange-600 " +
                       "my-2 mr-1 rounded focus:outline-none focus:ring-2 focus:ring-orange-700"
  const inactiveStyles = "bg-orange-300 text-white px-2 py-1 hover:bg-orange-600 " +
                         "my-2 mr-1 rounded focus:outline-none focus:ring-2 focus:ring-orange-700"

  return <button type="button" 
          className={(selectedMode === sortMode) ? activeStyles : inactiveStyles}
          onClick={handleClick}>{text}</button>
}

