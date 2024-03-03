import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../redux/store';
import { MobileApi } from '../types/MobileApi';
import { displayItems } from '../redux/productSlice';
import SearchBar from './SearchBar';
import BrandFilter from './BrandFilter';
import PriceSort from './PriceSort';
import Snackbar from './Snackbar'; 

const ProductList: React.FC = () => {
  const dispatch = useDispatch();
  const { filteredItems, status, error } = useSelector((state: RootState) => state.product);
  const [snackbarMessage, setSnackbarMessage] = useState<string>('');

  useEffect(() => {
    dispatch(displayItems(''));
  }, [dispatch]);

  useEffect(() => {
    if (status === 'loading') {
      setSnackbarMessage('Loading...'); 
    } else if (status === 'failed' && error) {
      setSnackbarMessage(error); 
    } else {
      setSnackbarMessage(''); 
    }
  }, [status, error]);

  return (
    <div>
      <div style={{ marginBottom: '10px', display: 'flex', alignItems: 'center' }}>
        <SearchBar />
        <BrandFilter />
        <PriceSort />
      </div>
      <div style={{ backgroundColor: '#f3f3f3', display: 'flex', flexWrap: 'wrap', gap: '1em', marginLeft: '15em', marginRight: '15em' }}>
        {filteredItems.map((product: MobileApi) => (
          <div key={product.id} style={{ flex: '1 0 calc(25% - 20px)', maxWidth: 'calc(25% - 20px)', border: '1px solid #ddd', borderRadius: '8px', overflow: 'hidden' }}>
            <a href={`/product/${product.id}`} style={{ textDecoration: 'none', color: 'inherit', display: 'block' }}>
              <div style={{ height: '300px', overflow: 'hidden', paddingTop: '10px' }}>
                <img src={product.image} alt={product.title} style={{ width: '100%', height: '100%', objectFit: 'cover' }} />
              </div>
              <div style={{ padding: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'flex-end', height: '70px' }}>
                <h2 style={{ margin: '0', fontSize: '16px', overflow: 'hidden', whiteSpace: 'nowrap' }}>{product.title.split(' ').slice(0, 4).join(' ')}</h2>
                <p style={{ margin: '0', fontSize: '14px', fontWeight: 'bold' }}>${product.price}</p>
              </div>
            </a>
          </div>
        ))}
      </div>
      {snackbarMessage && <Snackbar message={snackbarMessage} />} 
    </div>
  );
};

export default ProductList;


