import React, { Dispatch, SetStateAction } from 'react';
import { useTodo } from './TodoContext';
import TodoItem from './TodoItem';

interface TodoListProps {
  searchTerm: string;
  setSearchTerm: Dispatch<SetStateAction<string>>;
}

function TodoList({ searchTerm, setSearchTerm }: TodoListProps) {
  const { items, deleteItem } = useTodo();

  const handleDeleteItem = (id: number) => {
    return () => {
      deleteItem(id);
    };
  };

  // Filter items based on searchTerm
  const filteredItems = items.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <ul className="todo-list">
        {filteredItems.map(item => (
          <TodoItem key={item.id} id={item.id} name={item.name} onDeleteItem={handleDeleteItem(item.id)} />
        ))}
      </ul>
      {filteredItems.length === 0 && <p>No items found</p>}
    </div>
  );
}

export default TodoList;



