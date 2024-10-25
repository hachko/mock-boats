import { RoleName } from "./role-name-enum.model";

export interface AuthResponse {
    isValid: boolean;
    roles: RoleName[]
}