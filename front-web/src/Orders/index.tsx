import { useEffect, useState } from 'react';
import ProductsList from './ProductsList';
import StepsHeader from './StepsHeader';
import "./styles.css"
import { OrderLocationData, Product } from './types';
import {fetchProducts, saveOrder} from "../api";
import OrderLocation from './OrderLocation';
import OrderSummary from './OrderSummary';
import Footer from '../Footer';
import { checkIsSelected } from './helpers';
import { toast } from 'react-toastify';

const Orders = () => {
    const [products, setproducts] = useState<Product[]>([]);
    const [selectproducts, setselectproducts] = useState<Product[]>([]);
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [orderlocation, setorderlocation] = useState<OrderLocationData>();
    const totalPrice = selectproducts.reduce((sum, item) =>{
        return sum + item.price;
    }, 0);


    useEffect(() => {
        fetchProducts().then((response) =>{
            setproducts(response.data);
        }).catch(()=>{
            toast.error('Erro ao enviar pedido');
        });
    }, []);

    const handleSelectProduct = (product: Product) => {
        const isAlreadySelected = checkIsSelected(selectproducts, product);
      
        if (isAlreadySelected) {
          const selected = selectproducts.filter(item => item.id !== product.id);
          setselectproducts(selected);
        } else {
          setselectproducts(previous => [...previous, product]);
        }
    };
    const handleSubmit = () => {
        const productsIds = selectproducts.map(({ id }) => ({ id }));
        const payload = {
          ...orderlocation!,
          products: productsIds
        };
      
        saveOrder(payload).then((response) => {
          toast.info('Pedido enviado com sucesso! Nº ' + response.data.id);
          setselectproducts([]);
        })
        .catch(() => {
            toast.error('Erro ao enviar pedido');
        })
    };



    return (
        <>
            <div className="orders-container">
                <StepsHeader />
                <ProductsList products={products} onSelectProduct={handleSelectProduct} selectedProducts={selectproducts}/>
                <OrderLocation onChangeLocation={location => setorderlocation(location)}/>
                <OrderSummary amount={selectproducts.length} totalPrice={totalPrice} onSubmit={handleSubmit}/>
            </div>
            <Footer />
        </>
    )
}

export default Orders
