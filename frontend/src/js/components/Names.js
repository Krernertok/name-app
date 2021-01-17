import React from 'react'
import NameRow from './NameRow'
import Input from './Input'

const Names = ({names, inputValue, handleInputChange}) => {
  return (
    <div>
      <Input labelText="Filter names:" value={inputValue} handleChange={handleInputChange} />
      <table className="w-full my-2">
        <tbody>
          <tr className="text-lg bg-lightblue-800 text-white font-bold">
            <td className="px-2">Name</td>
            <td className="px-2">Amount</td>
          </tr>
          {names.map((name, index) => <NameRow key={name.name} name={name} index={index} />)}
          <tr className=" text-white font-bold bg-lightblue-800">
            <td className="px-2">Total</td>
            <td className="px-2">{names.reduce((acc, curr) => acc + curr.amount, 0)}</td>
          </tr>
        </tbody>
      </table>
    </div>
    
  )
}

export default Names