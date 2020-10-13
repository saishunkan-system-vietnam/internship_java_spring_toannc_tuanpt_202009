<template>
  <v-row>
    <v-col cols="12" sm="2" md="2"></v-col>
    <v-col cols="12" sm="8" md="8">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <v-row>
        <v-card
          cols="12"
          sm="3"
          md="3"
          class="portrait mt-2 ml-4"
          img="https://www.google.com/imgres?imgurl=https%3A%2F%2Fimage.shutterstock.com%2Fimage-photo%2Fbright-spring-view-cameo-island-260nw-1048185397.jpg&imgrefurl=https%3A%2F%2Fwww.shutterstock.com%2Fsearch%2Fnature&tbnid=PDxUM2uh-Nz6cM&vet=12ahUKEwidtdCZp7HsAhUFUpQKHf8vBLMQMygBegUIARDRAQ..i&docid=m4H9nlxeVf5uvM&w=475&h=280&q=image&ved=2ahUKEwidtdCZp7HsAhUFUpQKHf8vBLMQMygBegUIARDRAQ"
          height="120"
          width="120"
        ></v-card>
        <v-col cols="12" sm="3" md="3" class="pt-10">
          <h6>Player Name</h6>
          <p>Information</p>
        </v-col>
        <v-spacer></v-spacer>
        <v-card
          class="portrait mt-2 ml-4"
          img="https://www.google.com/imgres?imgurl=https%3A%2F%2Fimage.shutterstock.com%2Fimage-photo%2Fbright-spring-view-cameo-island-260nw-1048185397.jpg&imgrefurl=https%3A%2F%2Fwww.shutterstock.com%2Fsearch%2Fnature&tbnid=PDxUM2uh-Nz6cM&vet=12ahUKEwidtdCZp7HsAhUFUpQKHf8vBLMQMygBegUIARDRAQ..i&docid=m4H9nlxeVf5uvM&w=475&h=280&q=image&ved=2ahUKEwidtdCZp7HsAhUFUpQKHf8vBLMQMygBegUIARDRAQ"
          height="120"
          width="120"
        >
        </v-card>
      </v-row>
      <div class="mt-4"></div>
      <v-divider></v-divider>

      <strong>Last Matches</strong>
    </v-col>
    <v-col cols="12" sm="2" md="2"></v-col>
  </v-row>
</template>
<script>
export default {
  data: () => ({
    items: [
      {
        text: "Sport",
        disabled: false,
        href: "/",
      },
      {
        text: "Team",
        disabled: false,
        href: "/",
      },
      {
        text: "Player",
        disabled: true,
        href: "/",
      },
    ],
  }),
};
</script>