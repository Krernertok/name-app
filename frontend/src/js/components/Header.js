import React from 'react'

const Header = ({title}) => {
  const classes = "text-2xl text-lightblue-800 font-bold py-2 " +
                  "border-t-2 border-b-2 border-lightblue-800"
                  
  return <h1 className={classes}>{title}</h1>
}

export default Header