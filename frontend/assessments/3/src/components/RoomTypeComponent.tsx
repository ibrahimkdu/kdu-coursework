import React from 'react';

interface RoomTypeProps {
    roomType: string;
    selected: boolean;
    onRoomTypeSelected: () => void;
}

const RoomTypeComponent: React.FC<RoomTypeProps> = ({ roomType, selected, onRoomTypeSelected }) => {
    return (
        <div
            onClick={onRoomTypeSelected}
            style={
            {
                backgroundColor: selected ? "orange" : "lightgrey",
                padding: "10px",
                borderRadius: "5px",
                display: "flex"
            }
        }>
            {roomType}
        </div>
    );
};

export default RoomTypeComponent;
