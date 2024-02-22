
import React from "react";
import "./todoitem.scss";
import deleteIcon from "./delete.png";

interface TodoItemProps {
  id: number;
  name: string;
  onDeleteItem: (id: number) => void; // Add onDeleteItem prop
}

const TodoItem = ({ id, name, onDeleteItem }: TodoItemProps) => {
  const handleDelete = () => {
    onDeleteItem(id);
  };

  return (
    <div className="todo-item">
      <li key={id} className="todo-item-li">
        <div className="itemName">{name}</div>
        <div className="delete">
          <img
            src={deleteIcon}
            alt="Delete"
            className="delete-icon"
            onClick={handleDelete} // Pass handleDelete function here
          />
        </div>
      </li>
    </div>
  );
};

export default TodoItem;
