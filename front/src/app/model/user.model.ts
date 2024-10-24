import { Role } from "./role-enum.model";

export interface User {
    id: number | null,
    username: string,
    password: string,
    roles: Role[]
}