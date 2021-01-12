import React from 'react';
import { View, StyleSheet, Text, Alert, Linking } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import Header from '../Header';
import { Order } from '../types';
import OrderCard from '../OrderCard';
import { RectButton, ScrollView } from 'react-native-gesture-handler';
import { confirmDelivery } from '../api';

type Props = {
  route: {
    params: {
      order: Order;
    }
  }
};

export default function OrderDetails({ route }: Props) {
  const { order } = route.params;
  const navigation = useNavigation();

  const handleBackToHome = () => {
    navigation.navigate('Orders');
  };

  const handleConfirmDelivery = () => {
    confirmDelivery(order.id)
      .then(() => {
        Alert.alert(`Pedido ${order.id} confirmado com sucesso!`);
        handleBackToHome();
      })
      .catch(() => Alert.alert(`Ouve um erro ao confirmar o pedido ${order.id}`));
  };

  const handleStartNavigation = () => {
    Linking.openURL(`https://www.google.com/maps/dir/?api=1&travelmode=driving&dir_action=navigate&destination=${order.latitude},${order.longitude}`);
  };

  return (
    <>
      <Header />

      <ScrollView>
        <View style={styles.container}>
          <OrderCard order={order} />

          <RectButton onPress={handleStartNavigation} style={styles.button}>
            <Text style={styles.buttonText}>INICIAR NAVEGAÇÃO</Text>
          </RectButton>

          <RectButton onPress={handleConfirmDelivery} style={styles.button}>
            <Text style={styles.buttonText}>CONFIRMAR ENTREGA</Text>
          </RectButton>

          <RectButton onPress={handleBackToHome} style={styles.button}>
            <Text style={styles.buttonText}>CANCELAR</Text>
          </RectButton>
        </View>
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingRight: '5%',
    paddingLeft: '5%'
  },
  button: {
    backgroundColor: '#DA5C5C',
    flexDirection: 'row',
    borderRadius: 10,
    marginTop: 40,
    alignItems: 'center',
    justifyContent: 'center'
  },
  buttonText: {
    paddingTop: 15,
    paddingBottom: 15,
    paddingLeft: 50,
    paddingRight: 50,
    fontWeight: 'bold',
    fontSize: 18,
    color: '#FFF',
    letterSpacing: -0.24,
    fontFamily: 'OpenSans_700Bold'
  }
});