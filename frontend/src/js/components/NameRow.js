import React from 'react'

const NameRow = ({name, index}) => (
  <tr className={index % 2 === 0 ? "bg-lightblue-50" : "bg-lightblue-200"}>
    <td className="px-2">
      {name.name}
    </td>
    <td className="px-2">
      {name.amount}
    </td>
  </tr>
)

export default NameRow