import React from 'react'

const Button = ({text, handleClick}) => (
  <button type="button" 
          className="bg-emerald-500 text-white px-2 py-1 rounded hover:bg-emerald-700 active:bg-emerald-800 my-5"
          onClick={handleClick}>{text}</button>
)

export default Button