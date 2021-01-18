import React from 'react'

const Input = ({value, labelText, handleChange}) => (
  <div className="mt-5 bg-orange-500 ring-1 ring-orange-500 pl-2 py-1 flex">
    <label className=" text-white flex-1/6">{labelText}</label>
    <input className="ring-1 ring-orange-500 bg-white ml-2 mr-1 pl-1 flex-auto outline-none" value={value} onChange={handleChange} />
  </div>
)

export default Input