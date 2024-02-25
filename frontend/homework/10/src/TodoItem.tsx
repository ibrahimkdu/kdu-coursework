import React from "react";
import "./todoitem.scss";
import deleteIcon from "./delete.png";

interface TodoItemProps {
  id: number;
  name: string;
  onDeleteItem: (id: number) => void;
}

const TodoItem = ({ id, name, onDeleteItem }: TodoItemProps) => {
  const handleDeleteClick = () => {
    onDeleteItem(id);
  };

  return (
    <div className="todo-item">
      <li key={id} className="todo-item-li">
        <div className="itemName">{name}</div>
        <button
          className="delete"
          onClick={handleDeleteClick}
        >
          <img
            src={deleteIcon}
            alt="Delete"
            className="delete-icon"
          />
        </button>
      </li>
    </div>
  );
};

export default TodoItem;
