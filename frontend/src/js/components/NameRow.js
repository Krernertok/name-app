import React from 'react'

const NameRow = ({name}) => (
  <tr>
    <td>
      {name.name}
    </td>
    <td>
      {name.amount}
    </td>
  </tr>
)

export default NameRow