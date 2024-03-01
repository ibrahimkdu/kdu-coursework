import {Addon} from "./Addon.ts";

export interface RoomType {
    name: string;
    id: number;
    currency: string;
    costPerNight: string;
    addOns: Addon[];
}
