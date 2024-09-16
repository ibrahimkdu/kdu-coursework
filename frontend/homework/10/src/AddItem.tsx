import React, { useState } from "react";
import "./Additem.scss";

interface AddItemProps {
  onAddItem: (newItem: string) => void;
}

const AddItem = ({ onAddItem }: AddItemProps) => {
  const [newItem, setNewItem] = useState<string>("");

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewItem(event.target.value);
  };

  const handleAddItem = () => {
    if (newItem.trim() !== "") {
      onAddItem(newItem);
      setNewItem("");
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

