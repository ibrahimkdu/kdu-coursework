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

