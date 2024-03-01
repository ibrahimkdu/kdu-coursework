import { createSlice } from '@reduxjs/toolkit';
import { HotelAPI } from '../types/HotelApi';
import { GetRoom } from '../thunk/GetRoom';
interface RoomState{
    rooms:HotelAPI[];
    status: 'idle' | 'loading' | 'succeeded' | 'failed';
    error: string | null;
}
const initialState:RoomState={
    rooms:[],
    status:'idle',
    error:null,
};
const roomSlice = createSlice({
  name: 'rooms',
  initialState,
  reducers: {
      displayRoom(state) {
        return state;
    },        
  },
  extraReducers: (builder) => {
    builder
      .addCase(GetRoom.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(GetRoom.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.rooms = action.payload.roomTypes;
      })
      .addCase(GetRoom.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message ?? 'An error occurred.';
      });
  },
});

export const { displayRoom } = roomSlice.actions;
 export default roomSlice.reducer;
// import { createSlice } from '@reduxjs/toolkit';
// import { MobileApi } from '../types/MobileApi';
// import { getProducts } from '../thunk/getProducts';

// interface ProductState {
//   items: MobileApi[];
//   searchedItems: MobileApi[];
//   filteredItems: MobileApi[];
//   status: 'idle' | 'loading' | 'succeeded' | 'failed';
//   error: string | null;
// }

// const initialState: ProductState = {
//   items: [],
//   searchedItems: [],
//   filteredItems: [],
//   status: 'idle',
//   error: null,
// };

// const productSlice = createSlice({
//   name: 'product',
//   initialState,
//   reducers: {
//     displayItems(state, action) {
//       const searchString = action.payload;
//       state.searchedItems = state.items.filter((item) =>
//         item.title.toLowerCase().includes(searchString.toLowerCase())
//       );
//       state.filteredItems = state.searchedItems;
//     },
//     filterItems(state, action) {
//       const category = action.payload;
//       state.filteredItems = state.searchedItems.filter(
//         (product) => product.category.toLowerCase() === category.toLowerCase()
//       );
//     },
//     sortedItems(state, action) {
//       if (action.payload === 'asc') {
//         state.filteredItems = [...state.filteredItems].sort((a, b) => a.price - b.price);
//       } else if (action.payload === 'desc') {
//         state.filteredItems = [...state.filteredItems].sort((a, b) => b.price - a.price);
//       }
// .    },
//   },
//   extraReducers: (builder) => {
//     builder
//       .addCase(getProducts.pending, (state) => {
//         state.status = 'loading';
//       })
//       .addCase(getProducts.fulfilled, (state, action) => {
//         state.status = 'succeeded';
//         state.items = action.payload;
//         state.searchedItems = action.payload;
//         state.filteredItems = action.payload;
//       })
//       .addCase(getProducts.rejected, (state, action) => {
//         state.status = 'failed';
//         state.error = action.error.message ?? 'An error occurred.';
//       });
//   },
// });

// export const { displayItems, filterItems, sortedItems } = productSlice.actions;

// export default productSlice.reducer;
