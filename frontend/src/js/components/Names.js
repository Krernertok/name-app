import React from 'react'
import NameRow from './NameRow'

const Names = ({names}) => {
  return (
    <table className="w-full my-5">
      <tbody>
        <tr className="text-lg bg-emerald-600 text-white">
          <td className="px-2">Name</td>
          <td className="px-2">Amount</td>
        </tr>
        {names.map((name, index) => <NameRow key={name.name} name={name} index={index} />)}
      </tbody>
    </table>
  )
}

export default Names