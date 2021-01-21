import React from 'react'

const Input = ({value, labelText, handleChange}) => {
  const wrapperClasses = "mt-5 bg-orange-500 ring-1 ring-orange-500 rounded pl-2 py-1 flex"
  const labelClasses = "text-white flex-1/6"
  const inputClasses = "ring-1 ring-orange-500 bg-white ml-2 mr-1 pl-1 flex-auto outline-none"

  return (<div className={wrapperClasses}>
    <label className={labelClasses}>{labelText}</label>
    <input className={inputClasses} value={value} onChange={handleChange} />
  </div>)
}

export default Input