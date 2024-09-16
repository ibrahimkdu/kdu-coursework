import React, { useState } from 'react';
import './Todoapp.scss';
import Header from './Header';
import AddItem from './AddItem';
import TodoItem from './TodoItem';

interface TodoItem {
  id: number;
  name: string;
}

function TodoApp() {
  const [items, setItems] = useState<TodoItem[]>([
    { id: 1, name: 'Task 1' },
    { id: 2, name: 'Task 2' },
    { id: 3, name: 'Task 3' }
  ]);

  const [searchTerm, setSearchTerm] = useState<string>('');

  const handleAddItem = (newItemName: string) => {
    if (newItemName.trim() !== '') {
      const newItemObject: TodoItem = {
        id: Math.random(),
        name: newItemName
      };
      setItems([...items, newItemObject]);
    }
  };

  const handleDeleteItem = (id: number) => {
    const updatedItems = items.filter(item => item.id !== id);
    setItems(updatedItems);
  };

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const filteredItems = items.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="todo-app">
      <Header searchTerm={searchTerm} onSearchChange={handleSearch} />
      <div className="content">
        <div className="add-item">
          <AddItem onAddItem={handleAddItem} />
        </div>
        <div className="todo-items">
          {filteredItems.length > 0 ? (
            <ul className="todo-list">
              {filteredItems.map(item => (
                <TodoItem key={item.id} id={item.id} name={item.name} onDeleteItem={handleDeleteItem} />
              ))}
            </ul>
          ) : (
            <p className="no-match">No Match found</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default TodoApp;
