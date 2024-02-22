import React, { createContext, useContext, useState } from 'react';

interface TodoItem {
  id: number;
  name: string;
}

interface TodoContextType {
  items: TodoItem[];
  addItem: (newItemName: string) => void;
  deleteItem: (id: number) => void;
  searchTerm: string;
  setSearchTerm: (term: string) => void; // Add setSearchTerm function
}

const TodoContext = createContext<TodoContextType | undefined>(undefined);

export const TodoProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [items, setItems] = useState<TodoItem[]>([
    { id: 1, name: 'Task 1' },
    { id: 2, name: 'Task 2' },
    { id: 3, name: 'Task 3' }
  ]);
  const [searchTerm, setSearchTerm] = useState<string>('');

  const addItem = (newItemName: string) => {
    if (newItemName.trim() !== '') {
      const newItemObject: TodoItem = {
        id: Math.random(),
        name: newItemName
      };
      setItems([...items, newItemObject]);
    }
  };

  const deleteItem = (id: number) => {
    const updatedItems = items.filter(item => item.id !== id);
    setItems(updatedItems);
  };

  return (
    <TodoContext.Provider value={{ items, addItem, deleteItem, searchTerm, setSearchTerm }}>
      {children}
    </TodoContext.Provider>
  );
};

export const useTodo = () => {
  const context = useContext(TodoContext);
  if (!context) {
    throw new Error('useTodo must be used within a TodoProvider');
  }
  return context;
};
