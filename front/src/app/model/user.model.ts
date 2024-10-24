import { RoleName } from "./role-name-enum.model";

export interface Role {
    name: RoleName
}

export interface User {
    id: number | null,
    username: string,
    password: string,
    roles: Role[]
}