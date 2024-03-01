import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {RootState} from '../redux/store';
import {RoomType} from '../types/RoomType.ts';
import {displayRoom} from '../redux/RoomSlice';
import RoomTypeComponent from "./RoomTypeComponent.tsx";

export const Rooms: React.FC = () => {
    const dispatch = useDispatch();
    const {rooms} = useSelector((state: RootState) => state.rooms);
    const [selectedRoomType, setSelectedRoomType] = useState<RoomType>(null);

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
                padding: "10px",
                borderRadius: "5px",
                display: "block"
            }}>
                Select Room type
            </div>
            <div style={{
                display: 'flex',
                flexWrap: 'wrap',
                gap: '1em',
                marginLeft: '2em',
                marginRight: '2em'
            }}>
                {rooms.map((room: RoomType) => (
                    <RoomTypeComponent
                        onRoomTypeSelected={() => setSelectedRoomType(room)}
                        roomType={room.name} key={room.id} selected={room.id === selectedRoomType?.id}/>
                ))}
            </div>
            <div style={{
                padding: "10px",
                borderRadius: "5px",
                display: "block"
            }}>
                Select Date
            </div>
            <div style={{
                padding: "10px",
                borderRadius: "5px",
                display: "block"
            }}>
                Select Additional Addons/Preferences
            </div>
            {selectedRoomType ? (
                <div style={
                    {
                        display: 'flex',
                        flexWrap: 'wrap',
                        gap: '1em',
                    }
                }>
                    {selectedRoomType.addOns.map((addon, index) => (
                        <div
                            style={{
                                backgroundColor: "lightgrey",
                                padding: "10px",
                                borderRadius: "5px",
                                display: "flex"
                            }}
                            key={index}>
                            {addon.name}
                        </div>
                    ))}
                </div>
            ) : <div>Select Room Type first</div>}
        </div>
    );
};
  
