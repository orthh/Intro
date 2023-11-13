import React from 'react'
import { Plate, PlateContent } from '@udecode/plate-common';

type Props = {}
const initialValue = [
  {
    type: 'p',
    children: [
      {
        text: 'This is editable plain text with react and history plugins, just like a <textarea>!',
      },
    ],
  },
];

const BasicEditor = (props: Props) => {
  return (
    <Plate initialValue={initialValue}>
      <PlateContent placeholder="Type..." />
    </Plate>
  )
}

export default BasicEditor