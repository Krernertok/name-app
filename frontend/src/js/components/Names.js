import React from 'react'
import NameRow from './NameRow'

const Names = ({names}) => {
  return (
    <table>
      <tbody>
        <tr>
          <td>Name</td>
          <td>Amount</td>
        </tr>
        {names.map(name => <NameRow key={name.name} name={name} />)}
      </tbody>
    </table>
  )
}

export default Names