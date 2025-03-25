export interface User {
  id?: number;
  userName: string;
  password: string;
  role: 'USER' | 'ADMIN';
}
