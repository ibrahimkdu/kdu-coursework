import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface FilterState {
  search: string;
  filter: string;
  sort: string;
}

const initialState: FilterState = {
  search: "",
  filter: "",
  sort: "",
};

const filterSlice = createSlice({
  name: "filter",
  initialState,
  reducers: {
    updateSearch: (state, action: PayloadAction<string>) => {
      state.search = action.payload;
    },
    updateFilter: (state, action: PayloadAction<string>) => {
      state.filter = action.payload;
    },
    updateSort: (state, action: PayloadAction<string>) => {
      state.sort = action.payload;
    },
  },
});

export const { updateSearch, updateFilter, updateSort } = filterSlice.actions;
export default filterSlice.reducer;
