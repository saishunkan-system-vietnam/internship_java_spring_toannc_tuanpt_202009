<template>
  <div>
    <b-overlay
      :show="busy"
      rounded
      opacity="0.6"
      spinner-small
      spinner-variant="primary"
    ><h1 class="text-center">Tournament</h1>
      <v-btn
        class="mx-2"
        style="margin-bottom: 50px"
        dark
        color="indigo"
        @click.stop="dialog = true"
      >
        <v-icon dark> mdi-plus </v-icon></v-btn
      >
      <b-row>
        <b-col cols="12" sm="4">
          Name Tournament
          <b-form-input
            v-model="textNameTournament"
            placeholder="Input Field"
          ></b-form-input>
        </b-col>
        <b-col cols="12" sm="4">
          Status
          <b-form-select v-model="selectedStatus" :options="optionsStatus">
          </b-form-select>
        </b-col>
        <b-col cols="12" sm="4">
          Type
          <b-form-select v-model="selectedType" :options="optionsType">
          </b-form-select>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="10">
          Time Start To
          <b-row>
            <b-col cols="12" sm="5">
              <b-form-datepicker
                id="example-datepicker"
                v-model="valueStart"
                class="mb-2"
                reset-button
              >
              </b-form-datepicker
            ></b-col>
            <b-col cols="12" sm="1" class="text-center">~</b-col>
            <b-col cols="12" sm="5">
              <b-form-datepicker
                id="example-datepicker1"
                v-model="valueEnd"
                class="mb-2"
                reset-button
              ></b-form-datepicker
            ></b-col>
          </b-row>
        </b-col>
        <b-col>
          <br />
          <b-row>
            <b-col>
              <b-button variant="primary" @click="search">Search</b-button>
            </b-col>
          </b-row>
        </b-col>
      </b-row>

      <b-table
        id="my-table"
        :per-page="perPage"
        :current-page="currentPage"
        striped
        hover
        :items="items"
        :fields="fields"
      >
        <template v-slot:cell(Acction)="row">
          <router-link :to="{ path: '/DetailTournament/' + row.item.idTour }">
            <v-btn fab small>
              <v-icon>mdi-forward</v-icon>
            </v-btn>
          </router-link>
          <v-btn
            v-if="row.item.status == 0"
            class="mx-2"
            fab
            small
            @click="EditTour(row.item)"
          >
            <v-icon dark>mdi-pencil</v-icon>
          </v-btn>
          <v-btn
            v-if="row.item.status == 0"
            class="mx-2"
            fab
            small
            @click="deleteTour(row.item)"
          >
            <v-icon dark>mdi-delete</v-icon>
          </v-btn>
        </template>
        <template v-slot:cell(status)="row">
          <p
            :style="
              row.item.status == 0
                ? 'color:green'
                : row.item.status == 1
                ? 'color:blue'
                : 'color:red'
            "
          >
            {{
              row.item.status == 0
                ? "Upcomming"
                : row.item.status == 1
                ? "On Game"
                : "Finished"
            }}
          </p>
        </template>
      </b-table>
      <v-dialog v-model="dialogEdit" max-width="1000px" persistent>
        <v-card>
          <v-card-title class="headline"> Edit Tournament </v-card-title>

          <v-card-text>
            <EditTournament
              :callback="hideModalEdit"
              :getList="getListTournament"
              :editData="edit"
            ></EditTournament>
          </v-card-text>
        </v-card>
      </v-dialog>
      <b-modal id="modal-1" title="Warning" @ok="handleOk()">
        <p class="my-4">You sure want to delete?</p>
      </b-modal>
      <v-dialog v-model="dialog" max-width="1000px" persistent>
        <v-card>
          <v-card-title class="headline"> Create Tournament </v-card-title>

          <v-card-text>
            <AddTournament
              :callback="hideModal"
              :getList="getListTournament"
            ></AddTournament>
          </v-card-text>
        </v-card>
      </v-dialog>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </b-overlay>
  </div>
</template>
<script>
import AddTournament from "./AddTournament";
import EditTournament from "./EditTournament";
export default {
  components: {
    AddTournament,
    EditTournament,
  },
  data() {
    return {
      busy: true,
      textNameTournament: "",
      selectedStatus: null,
      selectedType: null,
      perPage: 10,
      currentPage: 1,
      loading: false,
      rows: "",
      index: "",
      idDelete: "",
      dialog: false,
      dialogEdit: false,
      edit: {},
      valueStart: "",
      valueEnd: "",
      tournament: [],
      fields: [
        {
          key: "nameTour",
          label: "Name Tournament",
          sortable: true,
        },
        {
          key: "type",
          label: "Type",
          sortable: true,
        },
        {
          key: "timeStart",
          label: "Time Start",
          sortable: true,
        },
        {
          label: "Time End",
          key: "timeEnd",
          sortable: true,
        },
        {
          key: "team.length",
          label: "Total Team",
          sortable: false,
        },
        {
          key: "status",
          label: "Status",
          sortable: true,
          sortDirection: "desc",
        },
        "Acction",
      ],
      items: [],
      optionsStatus: [
        { value: null, text: "Please select an option" },
        { value: "0", text: "Upcomming" },
        { value: "1", text: "On Game" },
        { value: "2", text: "Finished" },
      ],
      optionsType: [
        { value: null, text: "Please select an option" },
        { value: "Football", text: "Football" },
        { value: "TableTennis", text: "TableTennis" },
        { value: "BasketBall", text: "BasketBall" },
      ],
    };
  },
  methods: {
    getListTournament() {
      this.$store.dispatch("tournament/getAll").then((response) => {
        this.items = response.data;
        this.tournament = response.data;
        this.busy = false;
        this.rows=response.data.length
      });
    },
    EditTour(id) {
      this.edit = id;
      this.dialogEdit = true;
    },
    hideModalEdit() {
      this.dialogEdit = false;
    },
    hideModal() {
      this.dialog = false;
    },
    deleteTour(id) {
      this.$bvModal.show("modal-1");
      this.index = this.items.indexOf(id);
      this.idDelete = id.idTour;
    },
    handleOk() {
      this.$store
        .dispatch("tournament/delete", this.idDelete)
        .then((response) => {
          alert(response.data);
          this.items.splice(this.index);
          this.getListTournament();
        });
    },
    search() {
      this.busy = false;
      var arrSearch = [];
      {
        if (this.textNameTournament != "") {
          this.tournament.forEach((element) => {
            if (element.nameTour.includes(this.textNameTournament)) {
              arrSearch.push(element);
            }
          });
        } else {
          arrSearch = this.tournament;
        }
      }
      if (this.selectedStatus != null) {
        var arrStatus = [];
        arrSearch.forEach((element) => {
          if (element.status == this.selectedStatus) {
            arrStatus.push(element);
          }
        });
        arrSearch = arrStatus;
      }
      if (this.selectedType != null) {
        var arrType = [];
        arrSearch.forEach((element) => {
          if (element.type == this.selectedType) {
            arrType.push(element);
          }
        });
        arrSearch = arrType;
      }
      if (this.valueStart != "" && this.valueEnd != "") {
        var arrDate = [];
        arrSearch.forEach((element) => {
          if (
            element.timeStart >= this.valueStart &&
            element.timeStart <= this.valueEnd
          ) {
            arrDate.push(element);
          }
        });
        arrSearch = arrDate;
      }
      this.items = arrSearch;
      this.rows=arrSearch.length
    },
  },
  created() {
    this.$store.dispatch("tournament/updateStatus").then((response) => {
      this.getListTournament();
    });
  },
};
</script>
