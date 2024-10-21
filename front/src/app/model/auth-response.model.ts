import { Role } from "./role-enum;model";

export interface AuthResponse {
    isValid: boolean;
    roles: Role[]
}