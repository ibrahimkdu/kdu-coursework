import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {RootState} from '../redux/store';
import {HotelAPI} from '../types/HotelApi';
import {displayRoom} from '../redux/RoomSlice';
import RoomType from "./RoomType.tsx";

export const Rooms: React.FC = () => {
    const dispatch = useDispatch();
    const {rooms} = useSelector((state: RootState) => state.rooms);

    useEffect(() => {
        dispatch(displayRoom());
    }, [dispatch]);

    if (!rooms) {
        return <h1>Loading...</h1>;
    }

    return (
        <div>
            <h1>Hotel Booking</h1>
            <div style={{marginBottom: '10px', display: 'flex', alignItems: 'center'}}>
            </div>
            <div style={{
                backgroundColor: '#f3f3f3',
                display: 'flex',
                flexWrap: 'wrap',
                gap: '1em',
                marginLeft: '15em',
                marginRight: '15em'
            }}>
                <div style={{
                    padding: "10px",
                    borderRadius: "5px",
                    display: "block"
                }}>
                    Select Room type
                </div>
                {rooms.map((room: HotelAPI) => (
                        <RoomType roomType={room.name} key={room.id} />
                ))}
            </div>
        </div>
    );
};
  
