<template>
  <v-card>
    <v-card-title>
      <span class="headline">User Profile</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="6"> Image </v-col>
          <v-col cols="12" sm="6" md="6">
            <p>Name</p>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <p>Email</p>
          </v-col>
          <v-col cols="6">
            <p>Phone</p>
          </v-col>
        </v-row>
      </v-container>
      <small>*indicates required field</small>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="controlModalUser"> OK </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  props:{
    controlModalUser:{
      type: Function
    }
  }
}
</script>