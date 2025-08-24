export interface Product{
  productId?: number,
  imagePath?: string,
  price?: number,
  productName?: string,
  categoryId?: number,
  ownerId?: number,
  quantity?: number,
}

export interface OwnerProduct extends Product{
  dateAdded: string
}