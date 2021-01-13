import React, { useEffect, useState } from "react";
import {useIsFocused, useNavigation} from "@react-navigation/native";
import { Alert, StyleSheet, Text } from "react-native";
import {
  ScrollView,
  TouchableWithoutFeedback,
} from "react-native-gesture-handler";
import { fetchOrders } from "../api";
import Header from "../Header";
import OrderTile from "../OrderTile";
import { Order } from "../types";

export default function Orders() {
  const [orders, setorders] = useState<Order[]>([]);
  const [isloading, setisloading] = useState(false);
  const navigation = useNavigation();
  const isFocused = useIsFocused();

  const fetchData = () =>{
    setisloading(true);
    fetchOrders()
      .then((response) => {
        setorders(response.data);
      })
      .catch(() => {
        Alert.alert("An error occurred while trying to retrieve orders");
      })
      .finally(() => {
        setisloading(false);
      });
  }
  //toda vez que o valor de isfocused mudar
  //ele vai executar o codigo dentro do useeffect
  useEffect(() => {
    if (isFocused) {
      fetchData();
    }
  }, [isFocused]);

  const handleOnPress = (order: Order) =>{
    navigation.navigate("OrderDetails", {
      order
    });
  };

  return (
    <>
      <Header />
      <ScrollView style={styles.container}>
        {isloading ? (
          <Text>Buscando pedidos...</Text>
        ) : (
          orders.map((order) => (
            <TouchableWithoutFeedback onPress={() => handleOnPress(order)} key={order.id}>
              <OrderTile order={order}></OrderTile>
            </TouchableWithoutFeedback>
          ))
        )}
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingRight: "5%",
    paddingLeft: "5%",
  },
});
