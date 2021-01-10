import React from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';
import { TouchableWithoutFeedback } from 'react-native-gesture-handler';
import { useNavigation } from '@react-navigation/native';

export default function Header() {
  const navigation = useNavigation();

  const handleGoBack = () => {
    navigation.goBack();
  }

  return (
    <TouchableWithoutFeedback
      onPress={handleGoBack}
    >
      <View style={styles.container}>
        <Image source={require('../assets/logo.png')} />
        <Text style={styles.text}>DS Delivery</Text>
      </View>
    </TouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#DA5C5C',
    height: 80,
    paddingTop: 40,
    flexDirection: 'row',
    justifyContent: 'center',
  },

  text: {
    fontFamily: 'OpenSans_700Bold',
    fontWeight: 'bold',
    fontSize: 18,
    lineHeight: 25,
    letterSpacing: -0.24,
    color: '#fff',
    marginLeft: 15,
  },
});