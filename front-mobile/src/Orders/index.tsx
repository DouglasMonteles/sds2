import React, { useEffect, useState } from 'react';
import { useNavigation, useIsFocused } from '@react-navigation/native';
import { StyleSheet, ScrollView, Alert, Text } from 'react-native';
import { TouchableWithoutFeedback } from 'react-native-gesture-handler';

import Header from '../Header';
import OrderCard from '../OrderCard';
import { Order } from '../types';
import { fetchOrders } from '../api';

export default function Orders() {
  const [orders, setOrders] = useState<Order[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  const navigation = useNavigation();
  const isFocused = useIsFocused();

  const fetchData = () => {
    setIsLoading(true);
    fetchOrders()
      .then(response => setOrders(response.data))
      .catch(() => Alert.alert('Erro ao buscar os pedidos'))
      .finally(() => setIsLoading(false));
  };

  useEffect(() => {
    if (isFocused) {
      fetchData();
    }
  }, [isFocused]);

  const handleOrderDetails = (order: Order) => {
    navigation.navigate('OrderDetails', {
      order,
    });
  };

  return (
    <>
      <Header />
      <ScrollView style={styles.container}>
        {(isLoading) ? (
          <Text>Carregando pedidos...</Text>
        ) : (
            orders.map(order => (
              <TouchableWithoutFeedback
                key={order.id}
                onPress={() => handleOrderDetails(order)}
              >
                <OrderCard order={order} />
              </TouchableWithoutFeedback>
            ))
          )}
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingRight: '5%',
    paddingLeft: '5%',
  },
});