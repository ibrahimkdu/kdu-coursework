import React, { useEffect } from 'react';

interface SnackbarProps {
  message: string;
}

const Snackbar: React.FC<SnackbarProps> = ({ message }) => {
  useEffect(() => {
    const timeout = setTimeout(() => {
      document.getElementById('snackbar')?.remove(); 
    }, 5000);

    return () => clearTimeout(timeout);
  }, []);

  return (
    <div
      id="snackbar"
      style={{
        position: 'fixed',
        bottom: '20px',
        left: '50%',
        transform: 'translateX(-50%)',
        backgroundColor: '#333',
        color: '#fff',
        padding: '10px 20px',
        borderRadius: '5px',
        zIndex: '9999',
      }}
    >
        <h1>I am snackbar</h1>
      {message}
    </div>
  );
};

export default Snackbar;
