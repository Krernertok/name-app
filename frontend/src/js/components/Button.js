import React from 'react'

const Button = ({text, handleClick}) => (
  <button type="button" 
          className="bg-orange-500 text-white px-2 py-1 rounded hover:bg-orange-600 focus:bg-orange-600 my-2"
          onClick={handleClick}>{text}</button>
)

export default Button