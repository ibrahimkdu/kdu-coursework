import React from 'react';

interface RoomTypeProps {
    roomType: string;
}

const RoomType: React.FC<RoomTypeProps> = ({ roomType }) => {
    return (
        <div style={
            {
                backgroundColor: "orange",
                padding: "10px",
                borderRadius: "5px",
                display: "flex"
            }
        }>
            {roomType}
        </div>
    );
};

export default RoomType;
