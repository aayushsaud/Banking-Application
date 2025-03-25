export interface Account {
  id?: number; // Optional because it will be assigned by the backend
  bankName: string;
  balance: number;
  userId?: number; // Optional because it will be assigned by the backend
  amount?: number; // Add amount as an optional property
}
