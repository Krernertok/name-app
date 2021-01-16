import React from 'react'

const Names = ({names}) => {
  return (
    <table>
      <tbody>
        <tr>
          <td>Name</td>
          <td>Amount</td>
        </tr>
        {names.map(name => (
          <tr>
            <td>{name.name}</td>
            <td>{name.amount}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default Names