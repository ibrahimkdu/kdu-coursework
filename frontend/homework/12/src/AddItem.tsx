import React, { useState } from 'react';
import { useTodo } from './TodoContext';
import './Additem.scss';

const AddItem: React.FC = () => {
  const { addItem } = useTodo();
  const [newItem, setNewItem] = useState('');

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewItem(event.target.value);
  };

  const handleAddItem = () => {
    if (newItem.trim() !== '') {
      addItem(newItem);
      setNewItem('');
    }
  };

  return (
    <div className="add-item">
      <div className="add-header">Add items</div>
      <div className="add-items">
        <div className="add-bar">
          <input
            type="text"
            value={newItem}
            onChange={handleChange}
            className="input-field"
            placeholder="Type here..."
          />
        </div>
        <div className="add-heading">
          <button onClick={handleAddItem} className="submit-button">
            Submit
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddItem;
